extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global _java.io.OutputStream_CONSTRUCTOR_OutputStream#
_java.io.OutputStream_CONSTRUCTOR_OutputStream#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for OutputStream
extern _java.lang.Object_CONSTRUCTOR_Object#
call _java.lang.Object_CONSTRUCTOR_Object# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.OutputStream_METHOD_write#char#
_java.io.OutputStream_METHOD_write#char#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start Cast Expr *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg c
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
push eax
pop eax
; *****END Cast Expr *****
; *****END unaryexprnode *****
_java.io.OutputStream_BinOpEndLabel_1:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument b

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.OutputStream_noError_2
call __exception ; nullpointerexception (for methodcall)
_java.io.OutputStream_noError_2:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.OutputStream_METHOD_write#int#
_java.io.OutputStream_METHOD_write#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.OutputStream_BinOpEndLabel_3:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument b

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern NATIVEjava.io.OutputStream.nativeWrite
call NATIVEjava.io.OutputStream.nativeWrite ; call native method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****method _java.io.OutputStream_METHOD_nativeWrite#int# has no body *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.OutputStream_METHOD_flush#
_java.io.OutputStream_METHOD_flush#:
mov ebp, esp
; *****END MethodHeader *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****

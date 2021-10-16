extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start FieldDeclNode *****
global _java.lang.Byte_FIELD_value
_java.lang.Byte_FIELD_value:
mov ebp, esp
mov eax, 0 ; default initialization
mov ebx, [__THIS]
mov ebx, [ebx]
add ebx, 4
mov [ebx], eax ; initialize field
mov esp, ebp
ret ; return
 ; return from nonstatic field initializer
; *****END FieldDeclNode *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.Byte_CONSTRUCTOR_Byte#byte#
_java.lang.Byte_CONSTRUCTOR_Byte#byte#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Byte
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
; save registers
push esi
push ebx
push ecx
push edx
push ebp

call _java.lang.Byte_FIELD_value ; calls nonstatic field initializer
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.Byte_noError_1
call __exception ; nullpointerexception (field access)
_java.lang.Byte_noError_1:
add eax, 4 ; get address of nonstatic field value
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Byte_BinOpEndLabel_2:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.Byte_CONSTRUCTOR_Byte#
_java.lang.Byte_CONSTRUCTOR_Byte#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Byte
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
; save registers
push esi
push ebx
push ecx
push edx
push ebp

call _java.lang.Byte_FIELD_value ; calls nonstatic field initializer
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Byte_METHOD_toString#
_java.lang.Byte_METHOD_toString#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
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
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.Byte_noError_5
call __exception ; nullpointerexception (field access)
_java.lang.Byte_noError_5:
add eax, 4 ; get address of nonstatic field value
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Byte_BinOpEndLabel_4:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#byte#
call _java.lang.String_METHOD_valueOf#byte# ; call static method
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Byte_BinOpEndLabel_3:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****

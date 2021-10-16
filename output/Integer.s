extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start FieldDeclNode *****
global _java.lang.Integer_FIELD_value
_java.lang.Integer_FIELD_value:
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
global _java.lang.Integer_CONSTRUCTOR_Integer#int#
_java.lang.Integer_CONSTRUCTOR_Integer#int#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Integer
extern _java.lang.Number_CONSTRUCTOR_Number#
call _java.lang.Number_CONSTRUCTOR_Number# ; call constructor
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

call _java.lang.Integer_FIELD_value ; calls nonstatic field initializer
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
jne _java.lang.Integer_noError_1
call __exception ; nullpointerexception (field access)
_java.lang.Integer_noError_1:
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
_java.lang.Integer_BinOpEndLabel_2:
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
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Integer_METHOD_intValue#
_java.lang.Integer_METHOD_intValue#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax, [__THIS]
mov eax, [eax] ; get THIS for nonstatic field access
mov ebx, 0
cmp eax, ebx
jne _java.lang.Integer_noError_4
call __exception ; nullpointerexception (field access)
_java.lang.Integer_noError_4:
add eax, 4 ; get address of nonstatic field value
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_3:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Integer_METHOD_parseInt#String#
_java.lang.Integer_METHOD_parseInt#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_5:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local ret
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_6:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local neg
; *****END LocalVariableDeclNode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_7:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
; *****Start WhileStmt node *****
_java.lang.Integer_WhileLoop_8:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_12:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_14
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_14:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
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
_java.lang.Integer_BinOpEndLabel_13:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.lang.Integer_IfLT_15
mov eax, 0
jmp _java.lang.Integer_BinOpEndLabel_11
_java.lang.Integer_IfLT_15:
mov eax, 1
_java.lang.Integer_BinOpEndLabel_11:
; *****END BinOpExprNode *****
; *****Start AMP AMP LEFT *****
cmp eax, 1
jne _java.lang.Integer_AMPAMPLeftNotTrue_16
jmp _java.lang.Integer_AMPAMPLeftTrue_17
_java.lang.Integer_AMPAMPLeftNotTrue_16:
mov eax,0
jmp _java.lang.Integer_BinOpEndLabel_10
_java.lang.Integer_AMPAMPLeftTrue_17:
; *****END AMP AMP LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_22:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_23
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_23:
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_21:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 45
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_24:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.Integer_IfEQ_25
mov eax, 0
jmp _java.lang.Integer_BinOpEndLabel_20
_java.lang.Integer_IfEQ_25:
mov eax, 1
_java.lang.Integer_BinOpEndLabel_20:
; *****END BinOpExprNode *****
; *****Start OR OR LEFT *****
cmp eax, 1
je _java.lang.Integer_ORORLeftTrue_27
jmp _java.lang.Integer_ORORLeftNotTrue_26
_java.lang.Integer_ORORLeftTrue_27:
mov eax,1
jmp _java.lang.Integer_BinOpEndLabel_19
_java.lang.Integer_ORORLeftNotTrue_26:
; *****END OR OR LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_32:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_33
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_33:
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_31:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 48
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_34:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jge _java.lang.Integer_IfGE_35
mov eax, 0
jmp _java.lang.Integer_BinOpEndLabel_30
_java.lang.Integer_IfGE_35:
mov eax, 1
_java.lang.Integer_BinOpEndLabel_30:
; *****END BinOpExprNode *****
; *****Start AMP AMP LEFT *****
cmp eax, 1
jne _java.lang.Integer_AMPAMPLeftNotTrue_36
jmp _java.lang.Integer_AMPAMPLeftTrue_37
_java.lang.Integer_AMPAMPLeftNotTrue_36:
mov eax,0
jmp _java.lang.Integer_BinOpEndLabel_29
_java.lang.Integer_AMPAMPLeftTrue_37:
; *****END AMP AMP LEFT *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_40:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_41
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_41:
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_39:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 57
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_42:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jle _java.lang.Integer_IfLE_43
mov eax, 0
jmp _java.lang.Integer_BinOpEndLabel_38
_java.lang.Integer_IfLE_43:
mov eax, 1
_java.lang.Integer_BinOpEndLabel_38:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of AMPAMP *****
cmp ebx, 1
jne _java.lang.Integer_notEqualLabel_44
_java.lang.Integer_EqualLabel_45:
mov eax, 1
jmp _java.lang.Integer_BinOpEndLabel_29
_java.lang.Integer_notEqualLabel_44:
mov eax, 0
; *****END Right of AMPAMP *****
_java.lang.Integer_BinOpEndLabel_29:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_28:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of OROR *****
cmp ebx, 1
jne _java.lang.Integer_notEqualLabel_46
_java.lang.Integer_EqualLabel_47:
mov eax, 1
jmp _java.lang.Integer_BinOpEndLabel_19
_java.lang.Integer_notEqualLabel_46:
mov eax, 0
; *****END Right of OROR *****
_java.lang.Integer_BinOpEndLabel_19:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_18:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
; *****Start Right of AMPAMP *****
cmp ebx, 1
jne _java.lang.Integer_notEqualLabel_48
_java.lang.Integer_EqualLabel_49:
mov eax, 1
jmp _java.lang.Integer_BinOpEndLabel_10
_java.lang.Integer_notEqualLabel_48:
mov eax, 0
; *****END Right of AMPAMP *****
_java.lang.Integer_BinOpEndLabel_10:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.Integer_doneWhile_9
push esi; 
mov esi, esp ; block start
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_52:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_53
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_53:
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_51:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 45
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_54:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
je _java.lang.Integer_IfEQ_55
mov eax, 0
jmp _java.lang.Integer_BinOpEndLabel_50
_java.lang.Integer_IfEQ_55:
mov eax, 1
_java.lang.Integer_BinOpEndLabel_50:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.Integer_ElseStmt_56
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local neg
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local neg
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
mov ecx, 1
sub ecx, eax ; ecx <- 1-eax
mov eax, ecx ; eax <- 1-eax
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_58:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.Integer_IfStmtEnd_57
_java.lang.Integer_ElseStmt_56:
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_62:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 10
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_63:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
imul eax, ebx ; *
_java.lang.Integer_BinOpEndLabel_61:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
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
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_65:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.lang.Integer_noError_66
call __exception ; nullpointerexception (for methodcall)
_java.lang.Integer_noError_66:
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
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_64:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.Integer_beginNonStringAdd_67:
add eax, ebx ; +
_java.lang.Integer_endNonStringAdd_68:
_java.lang.Integer_BinOpEndLabel_60:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 48
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_69:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
sub eax, ebx ; -
_java.lang.Integer_BinOpEndLabel_59:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
_java.lang.Integer_IfStmtEnd_57:
; *****END Ifstmt node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -12 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_71:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_72:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.lang.Integer_beginNonStringAdd_73:
add eax, ebx ; +
_java.lang.Integer_endNonStringAdd_74:
_java.lang.Integer_BinOpEndLabel_70:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.lang.Integer_WhileLoop_8
_java.lang.Integer_doneWhile_9:
; *****END WhileStmt node *****
; *****Start Ifstmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -8 ; get address of local neg
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_75:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.lang.Integer_IfThenStmt_76
push esi; 
mov esi, esp ; block start
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
neg eax
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_77:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
_java.lang.Integer_IfThenStmt_76:
; *****END Ifstmt node *****
; *****Start ReturnStmt node *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local ret
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_78:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
; *****Start ConstructorDeclarationNode *****
global _java.lang.Integer_CONSTRUCTOR_Integer#String#
_java.lang.Integer_CONSTRUCTOR_Integer#String#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Integer
extern _java.lang.Number_CONSTRUCTOR_Number#
call _java.lang.Number_CONSTRUCTOR_Number# ; call constructor
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

call _java.lang.Integer_FIELD_value ; calls nonstatic field initializer
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
jne _java.lang.Integer_noError_79
call __exception ; nullpointerexception (field access)
_java.lang.Integer_noError_79:
add eax, 4 ; get address of nonstatic field value
; *****END NameNode *****
push eax ; store result of LHS
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
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_81:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
call _java.lang.Integer_METHOD_parseInt#String# ; call static method
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
_java.lang.Integer_BinOpEndLabel_80:
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
global _java.lang.Integer_CONSTRUCTOR_Integer#
_java.lang.Integer_CONSTRUCTOR_Integer#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for Integer
extern _java.lang.Number_CONSTRUCTOR_Number#
call _java.lang.Number_CONSTRUCTOR_Number# ; call constructor
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

call _java.lang.Integer_FIELD_value ; calls nonstatic field initializer
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
jne _java.lang.Integer_noError_82
call __exception ; nullpointerexception (field access)
_java.lang.Integer_noError_82:
add eax, 4 ; get address of nonstatic field value
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_83:
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
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.lang.Integer_METHOD_toString#
_java.lang.Integer_METHOD_toString#:
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
jne _java.lang.Integer_noError_86
call __exception ; nullpointerexception (field access)
_java.lang.Integer_noError_86:
add eax, 4 ; get address of nonstatic field value
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.lang.Integer_BinOpEndLabel_85:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#int#
call _java.lang.String_METHOD_valueOf#int# ; call static method
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
_java.lang.Integer_BinOpEndLabel_84:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov esp, ebp
ret ; return

; *****END ReturnStmt *****
; *****END MethodDeclaration *****
